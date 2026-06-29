# VidaPlena – Sistema de Gestão de Clínica Multidisciplinar (AV2 Refatorado)

## Descrição do Sistema

Sistema de gestão para a Clínica VidaPlena, desenvolvido em Java puro (console), aplicando rigorosamente os princípios de **Programação Orientada a Objetos** e **Clean Code**.

Nesta refatoração (AV2), a arquitetura do projeto foi inteiramente redesenhada para uma **Arquitetura Modular Baseada em Casos de Uso (Jornadas)**. A antiga e monolítica classe `ClinicaServico` foi extinta, distribuindo a complexidade de negócios em **33 classes isoladas**, garantindo que o software obedeça ao Princípio de Responsabilidade Única (SRP) do SOLID.

---

## Arquitetura Utilizada

A arquitetura escolhida adota o padrão **Command/Use Case**, onde cada regra de negócio é modelada como uma jornada de usuário que executa uma ação única no repositório. O projeto está dividido nos seguintes pacotes lógicos (`packages`):

- **`model`**: Contém todas as entidades de domínio (Paciente, Profissional, Consulta, Prontuario, Pagamento, etc) com seus encapsulamentos, composições e agregações.
- **`exceptions`**: Agrupa as classes de exceções customizadas (ex: `PacienteNaoEncontradoException`, `HorarioIndisponivelException`).
- **`repositorio`**: Contém a classe `ClinicaRepositorio`, responsável pela persistência em memória (usando `List`, `HashMap` e `HashSet`). Atua como o banco de dados simulado.
- **`jornadas`**: Substitui o antigo serviço central. Possui 33 classes (ex: `CadastroPacienteSimplificado`, `AgendamentoConsultaProfissional`), cada uma com um método `.executar()` que contém toda a lógica pertinente daquela jornada.
- **`relatorios`**: Pacote isolado contendo a lógica de exportação e visualização de dados gerenciais e tabelas financeiras (`GerarRelatorios`, `RelatorioFinanceiro`, etc).
- **`ui`**: Contém a classe `MenuPrincipal` que lida estritamente com I/O (Scanner e impressões na tela), livrando o ponto de entrada (`Main.java`) de responsabilidades que não lhe cabem.
- **`Main.java` (Root)**: Atua exclusivamente como o ponto de entrada seguro (*Entry Point*), capturando exceções globais críticas e instanciando o sistema inicial.

---

## Funcionalidades e Jornadas Atendidas (33 Classes)

Foram implementadas as 33 lógicas requisitadas para a AV2:

1.  **Jornadas de Pacientes:** `CadastroPacienteSimplificado`, `CadastroPacienteCompleto`, `CadastroPacienteControleDuplicidade`, `CompletacaoCadastro`, `BuscaInstantaneaPaciente` (via CPF no Map), `DesativacaoPaciente`, `AtivacaoPaciente`, `ControleUnicidadeCPF`, `CadastroValidacaoRobustaDados`.
2.  **Jornadas de Profissionais:** `CadastroAtualizacaoProfissionais`, `CadastroFisioterapeuta`, `CadastroPsicologo`, `DesativacaoProfissional`, `AtivacaoProfissional`, `GestaoHorarioDisponiveil`.
3.  **Jornadas de Consultas e Conflitos:** `AgendamentoConsultaProfissional`, `AgendamentoEspecialista`, `CancelamentoConsulta`, `RemarcacaoConsulta`, `AgendamentoPacienteInativo` (Bloqueio), `TratamentoConflitosAgenda` (Horários indisponíveis), `TratamentoConflitosHorario`.
4.  **Jornadas de Atendimentos:** `RegistroAtendimento`, `RegistroAtendimentoProntuario` (Composição), `RegistroEspecializacaoAtendimentoPsicologo`.
5.  **Jornadas de Pagamentos:** `ProcessamentoPagamentos`, `ProcessamentoPagamentoDinheiro`, `ProcessamentoPagamentoCartao`, `ProcessamentoPagamentoConvenio`, `VerificacaoCoberturaConvenio`, `TratamentoCompletoOperacaoInvalida`.
6.  **Jornadas Relatoriais (Pacote `relatorios`):** `RelatorioUnificadoCadastro` (downcasting/instanceof), `RelatorioGeralConsultas`, `RelatorioFinanceiro`, `RelatorioCancelamentos`, `RelatorioMultas`, `ExportacaoDadosOperacionais` (Interface `Exportavel` usando tabelas alinhadas no console).

---

## Hierarquia de Classes e Padrões OO

```text
Pessoa (abstrata)
├── Paciente
└── Profissional (abstrata)
    ├── Fisioterapeuta
    ├── Psicologo
    ├── Nutricionista
    └── ClinicoGeral

Pagamento (abstrata)
├── PagamentoDinheiro
├── PagamentoCartao
└── PagamentoConvenio

Interfaces:
├── Agendavel       (implementada por: Consulta)
└── Exportavel      (implementada por: Consulta, Atendimento, Pagamento)

Relacionamentos:
├── Composição:  Atendimento → Prontuario
├── Agregação:   Profissional → HorarioDisponivel
└── Associação:  Paciente → Convenio
```

---

## Compilação e Execução

Para rodar a nova estrutura separada em pacotes com *Encoding* atualizado:

### No Windows (CMD / PowerShell)
```cmd
mkdir bin
javac -encoding UTF-8 -d bin -sourcepath src src\Main.java
java -cp bin Main
```

### No Linux/Mac (Bash)
```bash
mkdir -p bin
javac -encoding UTF-8 -d bin -sourcepath src src/Main.java
java -cp bin Main
```

---

## O que foi melhorado na AV2?

- **Fim da classe "Deus"**: Remoção da classe que geria tudo (`ClinicaServico`), descentralizando as regras para o padrão de projeto `Command` no pacote de jornadas.
- **Velocidade na Busca**: Uso de `HashMap` e `HashSet` no Repositório (Ex: busca instantânea de pacientes por CPF usando chave primária - `O(1)`).
- **Proteção Completa**: O código é agora protegido contra exceções, utilizando as sub-classes desenvolvidas no pacote `exceptions` (Ex: `PacienteNaoEncontradoException`, `HorarioIndisponivelException`).
- **Clean Code e SOLID aplicados**:
  - **Single Responsibility Principle (SRP)**: Pacote `ui` apenas faz UI. Pacote `jornadas` apenas executa a regra de negócio. Pacote `repositorio` apenas persiste dados em memória.
  - **Dependency Inversion Principle (DIP)**: O MenuPrincipal depende das instâncias injetadas via construtores e o Repositório é injetado nas lógicas.