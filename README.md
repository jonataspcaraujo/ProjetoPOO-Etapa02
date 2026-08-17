# VidaPlena — Sistema de Gestão de Clínica Multidisciplinar

Sistema de gestão clínica desenvolvido em Java orientado a objetos, com suporte a cadastro de pacientes e profissionais de saúde, agendamento de consultas, registro de atendimentos com prontuário e processamento de pagamentos.
 
---

## Visão Geral

O **VidaPlena** é um sistema de console desenvolvido como projeto acadêmico para a disciplina de Programação Orientada a Objetos. Ele simula o funcionamento interno de uma clínica multidisciplinar, permitindo que uma recepcionista gerencie o ciclo completo de atendimento: do cadastro do paciente até o processamento do pagamento.

O sistema foi construído com foco na aplicação prática dos principais conceitos de POO em Java, como herança em múltiplos níveis, polimorfismo, ligação dinâmica, interfaces, composição, agregação, associação e tratamento estruturado de exceções.
 
---

## Funcionalidades

### Gestão de Pacientes
- **Cadastro mínimo** — nome e CPF, disponível imediatamente para agendamentos.
- **Cadastro intermediário** — acrescenta idade e telefone ao cadastro mínimo.
- **Cadastro completo** — inclui também informações de convênio (`saúdeplus`, `vidamais` ou `bemestar`).
- **Complementação de cadastro** — atualiza dados de um paciente já cadastrado (idade, telefone e/ou convênio).
- **Busca por CPF** — localiza e exibe os dados de um paciente individualmente.
- **Listagem de pacientes** — exibe todos os pacientes cadastrados no sistema.
- **Desativação de paciente** — altera o status para inativo, impedindo novos agendamentos.
- **Controle de duplicidade de CPF** — impede o cadastro de dois pacientes com o mesmo CPF via `HashSet`.
### Gestão de Profissionais
- **Cadastro mínimo** — nome, CPF e especialidade.
- **Cadastro completo** — inclui registro profissional e valor da consulta.
- **Atualização de cadastro** — permite informar registro, valor da consulta e dias de disponibilidade.
- **Listagem de profissionais** — exibe todos os profissionais cadastrados.
- **Filtro por especialidade** — lista apenas profissionais de uma especialidade específica (clínica geral, fisioterapia, nutrição ou psicologia).
- **Gestão de horários disponíveis** — vincula objetos `HorarioDisponivel` ao profissional.
### Gestão de Consultas
- **Agendamento com escolha de profissional** — informa CPF do paciente, nome do profissional, data, horário e tipo da consulta.
- **Cancelamento de consulta** — localiza pelo CPF + data + horário e altera status para `cancelada`, com registro opcional do motivo.
- **Remarcação de consulta** — altera data e horário de uma consulta existente.
- **Listagem de todas as consultas** — relatório geral com todos os registros.
- **Busca de consultas por CPF** — exibe todas as consultas de um paciente, com seus índices na lista.
- **Detecção de conflito de horário** — impede agendamento quando o profissional já possui consulta no mesmo dia e horário.
- **Validação de formato de horário** — rejeita horários fora do padrão `HH:mm`.
### Registro de Atendimento
- **Registro de atendimento** — vincula observações clínicas e diagnóstico a uma consulta existente.
- **Adição de procedimentos** — permite informar quantos procedimentos forem necessários durante o atendimento.
- **Prontuário automático** — criado automaticamente junto com o atendimento, armazenando observações, diagnóstico e lista de procedimentos.
- **Observação especializada** — campo adicional para registros específicos da especialidade do profissional.
- **Marcação da consulta como realizada** — altera o status da consulta para `realizada` ao concluir o atendimento.
### Processamento de Pagamentos
- **Pagamento em dinheiro/Pix** — aplica 5% de desconto sobre o valor base da consulta.
- **Pagamento com cartão de crédito** — permite parcelamento; acima de 3 parcelas, aplica taxa de 2,5% por parcela extra.
- **Pagamento por convênio** — aplica percentual de cobertura conforme o plano: SaúdePlus (40%), VidaMais (30%) ou BemEstar (50%). Para convênios cadastrados com percentual personalizado, utiliza o valor do atributo.
- **Listagem de pagamentos** — exibe todos os pagamentos registrados.
### Relatórios
- **Resumo de consultas** — relatório geral de todas as consultas com status.
- **Relatório por profissional** — filtra e exibe consultas de um profissional específico.
- **Resumo financeiro** — lista todos os pagamentos registrados.
### Exportação de Dados
- Qualquer objeto que implemente `Exportavel` pode ter seus dados serializados em texto pelo método `exportarDados()`. Está implementado em `Consulta`, `Atendimento`, `PagamentoPix`, `PagamentoCartao` e `PagamentoConvenio`.
---

## Tecnologias Utilizadas

- **Java** (JDK 11 ou superior)
- **Scanner** — leitura de dados pelo terminal (`System.in`)
- **System.out** — saída de dados no console
- **Java Collections Framework**
    - `ArrayList` — lista de consultas, atendimentos, pagamentos, pessoas e horários disponíveis
    - `HashSet` — controle de unicidade de CPFs cadastrados
    - `HashMap` — busca rápida de pacientes por CPF e profissionais por nome
---

## Estrutura do Projeto

Todas as classes estão no pacote padrão (sem pacote declarado), organizadas em arquivos `.java` individuais:

```
src/
 ├── Main.java                        # Ponto de entrada; menus e leitura de dados do usuário
 ├── ClinicaServico.java              # Concentra toda a lógica de negócio da clínica
 │
 ├── model/pessoas/
 │    ├── Pessoa.java                 # Superclasse base (não abstrata no código)
 │    ├── Paciente.java               # Especialização de Pessoa
 │    └── Profissional.java           # Superclasse abstrata intermediária
 │         ├── ClinicoGeral.java
 │         ├── Fisioterapeuta.java
 │         ├── Nutricionista.java
 │         └── Psicologo.java
 │
 ├── model/consulta/
 │    ├── Agenda.java                 # Gerencia a lista de consultas e operações sobre ela
 │    └── Consulta.java               # Representa uma consulta (implements Agendavel, Exportavel)
 │
 ├── model/atendimento/
 │    ├── Atendimento.java            # Registro clínico (implements Exportavel)
 │    └── Prontuario.java             # Composta dentro de Atendimento
 │
 ├── model/pagamento/
 │    ├── Pagamento.java              # Superclasse abstrata
 │    ├── PagamentoPix.java           # implements Exportavel
 │    ├── PagamentoCartao.java        # implements Exportavel
 │    └── PagamentoConvenio.java      # implements Exportavel
 │
 ├── model/outros/
 │    ├── Convenio.java               # Entidade de convênio associada ao Paciente
 │    └── HorarioDisponivel.java      # Objeto de horário agregado ao Profissional
 │
 ├── interfaces/
 │    ├── Agendavel.java              # cancelar(), remarcar()
 │    └── Exportavel.java             # exportarDados()
 │
 └── exceptions/
      ├── HorarioIndisponivelException.java
      └── OperacaoInvalidaException.java
```

> **Nota:** os arquivos estão no pacote padrão; a estrutura de diretórios acima representa a organização lógica, não a estrutura de pastas real do repositório.
 
---

## Arquitetura do Sistema

O sistema segue uma separação clara entre três camadas:

**Camada de Interface (Main.java)** — responsável exclusivamente por exibir menus, ler entradas do usuário via `Scanner` e chamar os métodos da camada de serviço. Não contém regras de negócio.

**Camada de Serviço (ClinicaServico.java)** — concentra toda a lógica de negócio. Gerencia as coleções (`HashMap`, `HashSet`, `ArrayList`), chama os métodos dos modelos, trata exceções e delega operações sobre consultas para a classe `Agenda`.

**Camada de Modelo** — classes que representam as entidades do domínio (`Paciente`, `Profissional`, `Consulta`, `Atendimento`, `Pagamento`, etc.). Cada entidade encapsula seus dados e comportamentos próprios.

O fluxo típico de uma operação é: `Main` lê os dados → chama método em `ClinicaServico` → `ClinicaServico` manipula as coleções e instâncias do modelo → resultado é exibido no console.
 
---

## Principais Classes

| Classe | Responsabilidade |
|---|---|
| `Main` | Ponto de entrada. Exibe todos os menus e lê os dados do usuário via `Scanner`. |
| `ClinicaServico` | Concentra as regras de negócio: gerencia coleções de pacientes, profissionais, atendimentos e pagamentos. |
| `Agenda` | Gerencia a lista de consultas. Realiza agendamento, cancelamento, remarcação, busca por CPF e detecção de conflitos de horário. |
| `Pessoa` | Superclasse base com atributos `nome` e `cpf`, getters, setters com validação e método `exibirDados()`. |
| `Paciente` | Especializa `Pessoa` com telefone, idade, status e associação com `Convenio`. |
| `Profissional` | Classe abstrata que estende `Pessoa`. Adiciona registro, valor de consulta e lista de horários disponíveis. Declara `exibirResumo()` como abstrato. |
| `ClinicoGeral` | Estende `Profissional`. Atributo `encaminhamento`. Sobrescreve `exibirResumo()`. |
| `Fisioterapeuta` | Estende `Profissional`. Atributo `totalSessoesPrevistas`. Sobrescreve `exibirResumo()`. |
| `Nutricionista` | Estende `Profissional`. Atributo `planoAlimentar`. Sobrescreve `exibirResumo()`. |
| `Psicologo` | Estende `Profissional`. Atributo `abordagem`. Sobrescreve `exibirResumo()`. |
| `Consulta` | Implementa `Agendavel` e `Exportavel`. Armazena CPF do paciente, nome do profissional, data, horário, tipo e status. |
| `Atendimento` | Implementa `Exportavel`. Registra observações, diagnóstico, procedimentos e possui `Prontuario` por composição. |
| `Prontuario` | Criado exclusivamente dentro de `Atendimento`. Armazena observações, diagnóstico e lista de procedimentos. |
| `Convenio` | Representa o convênio do paciente com nome, percentual de cobertura e lista de especialidades cobertas. |
| `HorarioDisponivel` | Representa um dia da semana e horário. É agregado ao `Profissional`. |
| `Pagamento` | Classe abstrata. Define `calcularValorFinal()` (abstrato) e `exibirResumo()` (concreto). |
| `PagamentoPix` | Estende `Pagamento`. Aplica 5% de desconto. Implementa `Exportavel`. |
| `PagamentoCartao` | Estende `Pagamento`. Aplica taxa de 2,5% por parcela acima de 3. Implementa `Exportavel`. |
| `PagamentoConvenio` | Estende `Pagamento`. Aplica percentual de cobertura do convênio. Implementa `Exportavel`. |
 
---

## Hierarquia de Classes

```
Pessoa
├── Paciente
└── Profissional  (abstrata)
    ├── ClinicoGeral
    ├── Fisioterapeuta
    ├── Nutricionista
    └── Psicologo
 
Pagamento  (abstrata)
├── PagamentoPix
├── PagamentoCartao
└── PagamentoConvenio
```

**`Pessoa`** — base da hierarquia de participantes. Concentra `nome` e `cpf` com validação nos setters.

**`Paciente`** — adiciona `telefone`, `idade`, `status` e associação com `Convenio`. Sobrescreve `setNome()`, `setCpf()` e `exibirDados()`.

**`Profissional`** — nível intermediário abstrato. Adiciona `registro`, `valorConsulta` e a lista de `HorarioDisponivel`. Declara `exibirResumo()` como método abstrato, forçando cada especialidade a implementar sua própria apresentação.

**`ClinicoGeral`, `Fisioterapeuta`, `Nutricionista`, `Psicologo`** — terceiro nível da hierarquia. Cada um adiciona atributo(s) específico(s) da especialidade e sobrescreve `exibirResumo()`.

**`Pagamento`** — hierarquia paralela de formas de pagamento. Cada subclasse implementa `calcularValorFinal()` com a lógica financeira da sua modalidade.
 
---

## Interfaces

### `Agendavel`
Define os contratos de operações de agenda: `cancelar()` e `remarcar()`, ambos declarados com `throws Exception`.

Implementada por: **`Consulta`**

### `Exportavel`
Define o contrato `exportarDados()`, que retorna uma `String` com os dados do objeto em formato serializado (separado por `;` ou `|`).

Implementada por: **`Consulta`**, **`Atendimento`**, **`PagamentoPix`**, **`PagamentoCartao`**, **`PagamentoConvenio`**

`Consulta` implementa as duas interfaces simultaneamente, demonstrando herança múltipla de tipos em Java.
 
---

## Conceitos de POO Utilizados

### Encapsulamento
Todos os atributos das classes são privados ou protegidos. O acesso externo ocorre exclusivamente via getters e setters. Setters críticos realizam validação interna: `setIdade()` em `Paciente` rejeita valores negativos ou acima de 100; `setNome()` e `setCpf()` em `Pessoa` (e sobrescritos em `Paciente`) rejeitam valores nulos ou vazios; `setpercentualConvenio()` em `Convenio` rejeita valores fora do intervalo [0, 1].

### Herança
Hierarquia de três níveis: `Pessoa → Profissional → ClinicoGeral/Fisioterapeuta/Nutricionista/Psicologo`. Todos os construtores de subclasses chamam `super(...)` explicitamente. `Paciente` herda de `Pessoa` e `Profissional` é superclasse abstrata das especializações profissionais.

### Classe Abstrata
`Profissional` é abstrata e declara `exibirResumo()` como abstrato, obrigando cada especialidade a definir seu próprio comportamento de exibição. `Pagamento` é abstrata e declara `calcularValorFinal()` como abstrato, garantindo que cada forma de pagamento implemente seu próprio cálculo.

### Sobrescrita (`@Override`)
`exibirResumo()` é sobrescrito em `ClinicoGeral`, `Fisioterapeuta`, `Nutricionista` e `Psicologo`. `exibirDados()` é sobrescrito em `Paciente`. `cancelar()`, `remarcar()` e `exportarDados()` são sobrescritos em `Consulta`. `calcularValorFinal()`, `exibirResumo()` e `exportarDados()` são sobrescritos em `PagamentoPix`, `PagamentoCartao` e `PagamentoConvenio`.

### Sobrecarga
`Atendimento` possui três construtores sobrecarregados (básico, com diagnóstico, e completo com procedimentos) e dois métodos `adicionarProcedimento()` (um por unidade e um por array). `ClinicaServico` possui dois métodos `atualizarPaciente()` e dois `atualizarProfissional()` e dois `listarProfissionais()`. `Pagamento` possui três métodos estáticos `calcularValor()` sobrecarregados. `Paciente`, `Profissional` e suas subclasses possuem múltiplos construtores sobrecarregados.

### Polimorfismo e Ligação Dinâmica
`ClinicaServico` mantém uma `List<Pessoa>` (`todasAsPessoas`) com pacientes e profissionais misturados. O método `exibirDados()` é chamado polimorficamente ao iterar essa lista — o comportamento executado depende do tipo real do objeto em tempo de execução. O mesmo ocorre com `List<Pagamento>`: ao chamar `exibirResumo()` em cada pagamento, cada subclasse exibe suas informações específicas.

### Casting e `instanceof`
Em `ClinicaServico`, ao filtrar a lista `todasAsPessoas`, `instanceof` é usado para identificar o tipo real (`Paciente`, `Profissional`, `ClinicoGeral`, `Fisioterapeuta`, etc.) antes de exibir informações específicas.

### Interfaces e Herança Múltipla de Tipos
`Consulta` implementa `Agendavel` e `Exportavel` simultaneamente. As três subclasses de `Pagamento` também implementam `Exportavel`, e `Atendimento` também implementa `Exportavel`, totalizando cinco classes implementando a mesma interface.

### Associação
`Consulta` conhece o CPF do `Paciente` e o nome do `Profissional` (referências por chave). `Paciente` possui referência direta a `Convenio` — ambos existem independentemente.

### Agregação
`Profissional` possui uma `List<HorarioDisponivel>`. Os objetos `HorarioDisponivel` são criados externamente e adicionados ao profissional, podendo existir independentemente dele.

### Composição
`Atendimento` cria e possui um `Prontuario` internamente. O `Prontuario` é instanciado dentro do construtor de `Atendimento` e não é acessível externamente para criação — ele não existe sem o atendimento.

### Tratamento de Exceções
As exceções personalizadas são lançadas pelos métodos de serviço (`throws`) e capturadas no chamador com blocos `catch` específicos. `HorarioIndisponivelException` e `OperacaoInvalidaException` possuem dois construtores cada (sobrecarga): um que recebe apenas mensagem e outro que recebe mensagem e causa (`Throwable`).

### Coleções
`ArrayList` para listas ordenadas (consultas, atendimentos, pagamentos, pessoas, horários). `HashSet` para garantir unicidade de CPFs antes do cadastro. `HashMap<String, Paciente>` e `HashMap<String, Profissional>` para busca direta por CPF e por nome.
 
---

## Fluxo Geral do Sistema

Ao iniciar, o sistema exibe o menu principal com seis módulos. A navegação é feita por números digitados no console.

```
=== CLINICA VIDAPLENA ===
1 - Pacientes
2 - Profissionais
3 - Consultas
4 - Atendimentos
5 - Pagamentos
6 - Relatorios
0 - Sair
```

Cada módulo possui seu próprio submenu. O sistema retorna ao menu principal ao digitar `0` em qualquer submenu.
 
---

## Como Compilar

Certifique-se de ter o **JDK 11 ou superior** instalado. Com todos os arquivos `.java` no mesmo diretório, execute:

```bash
javac *.java
```
 
---

## Como Executar

Após a compilação, execute a classe principal:

```bash
java Main
```
 
---

## Como Utilizar

A seguir, o fluxo completo de utilização do sistema, do cadastro ao pagamento.

### 1. Cadastrar um Paciente

No menu principal, escolha **1 (Pacientes)** → **1 (Cadastrar)**.

Informe nome e CPF. Em seguida, o sistema solicita o tipo de cadastro:
- `1` — mínimo (apenas nome e CPF)
- `2` — com idade e telefone
- `3` — completo, solicitando também nome do convênio (`saúdeplus`, `vidamais` ou `bemestar`)
### 2. Cadastrar um Profissional

No menu principal, escolha **2 (Profissionais)** → **1 (Cadastrar)**.

Informe nome, especialidade (`clinica geral`, `fisioterapia`, `psicologia` ou `nutricao`) e tipo de cadastro:
- `1` — mínimo (nome, CPF e especialidade)
- `2` — completo, solicitando registro profissional e valor da consulta
  Para atualizar o profissional com dias de atendimento, use **2 (Atualizar cadastro)**, informando o nome, registro, valor e, opcionalmente, os dias disponíveis.

### 3. Agendar uma Consulta

No menu principal, escolha **3 (Consultas)** → **1 (Agendar)**.

Informe CPF do paciente, nome do profissional, data no formato `DD/MM/AAAA` e horário no formato `HH:MM`. O sistema valida o formato do horário e verifica conflitos antes de confirmar o agendamento.

### 4. Registrar um Atendimento

Após a consulta ter sido realizada, acesse **4 (Atendimentos)** → **1 (Registrar atendimento)**.

Informe o CPF do paciente — o sistema exibe as consultas cadastradas com seus índices. Selecione o índice da consulta, informe as observações clínicas e o diagnóstico. O sistema pergunta se deseja adicionar procedimentos; caso sim, informe a quantidade e o nome de cada um. A consulta é marcada como `realizada` automaticamente.

### 5. Processar um Pagamento

Acesse **5 (Pagamentos)** → **1 (Registrar pagamento)**.

Informe o CPF do paciente. O sistema exibe as consultas e solicita o índice daquela que será paga. Escolha a forma de pagamento:
- `1` — Pix/Dinheiro (5% de desconto automático)
- `2` — Cartão (informe número de parcelas e bandeira; acima de 3x, aplicada taxa de 2,5% por parcela extra)
- `3` — Convênio (desconto automático conforme plano do paciente)
### 6. Emitir Relatórios

Acesse **6 (Relatórios)** e escolha:
- `1` — Resumo de todas as consultas
- `2` — Consultas por profissional (informe o nome)
- `3` — Resumo financeiro (todos os pagamentos)
---

## Estruturas de Dados

### `ArrayList`
Utilizado em `ClinicaServico` para armazenar `todasAsPessoas`, `atendimentos` e `pagamentos`. Utilizado em `Agenda` para armazenar `listaConsultas`. Utilizado em `Profissional` para armazenar `horariosDisponiveis`. Utilizado em `Prontuario` de forma indireta (via array interno).

Justificativa: a ordem de inserção é relevante para relatórios cronológicos e o acesso por índice é necessário para busca e seleção de consultas pelo usuário.

### `HashSet<String>`
Utilizado em `ClinicaServico` como `cpfsCadastrados`.

Justificativa: apenas a verificação de existência (`contains()`) é necessária antes de inserir um CPF. Não há necessidade de ordenação, e a operação de verificação é O(1).

### `HashMap<String, Paciente>` e `HashMap<String, Profissional>`
Utilizados em `ClinicaServico` como `mapaPacientes` (chave: CPF) e `mapaProfissionais` (chave: nome).

Justificativa: a busca direta por chave é mais eficiente do que percorrer uma lista. Operações `get()` e `containsKey()` em O(1).
 
---

## Tratamento de Exceções

### `HorarioIndisponivelException`
Lançada por `Agenda.agendarConsulta()` quando já existe uma consulta com status `agendada` para o mesmo profissional, data e horário. Possui dois construtores: `(String mensagem)` e `(String mensagem, Throwable causa)`.

### `OperacaoInvalidaException`
Lançada em múltiplos cenários: tentativa de cancelar uma consulta com status `realizada`; tentativa de remarcar consulta `realizada` ou `cancelada`; tentativa de realizar uma consulta `cancelada`; horário informado com formato inválido; nenhuma consulta encontrada para os critérios informados. Possui dois construtores: `(String mensagem)` e `(String mensagem, Throwable causa)`.

As exceções são declaradas com `throws` nos métodos de `Agenda`, capturadas em `ClinicaServico` e a mensagem de erro é exibida ao usuário sem encerrar o sistema.
 
---

## Regras de Negócio

- CPF duplicado é rejeitado antes do cadastro por verificação no `HashSet`.
- O horário de agendamento deve estar no formato `HH:mm` com hora entre 0–23 e minutos entre 0–59.
- Não é possível agendar um profissional no mesmo dia e horário de outra consulta com status `agendada`.
- Não é possível cancelar ou remarcar uma consulta com status `realizada`.
- Não é possível remarcar uma consulta com status `cancelada`.
- Não é possível realizar (registrar atendimento em) uma consulta com status `cancelada`.
- Pagamento com cartão em até 3 parcelas não tem acréscimo; acima de 3 parcelas, aplica-se 2,5% por parcela extra.
- O convênio SaúdePlus cobre 40% do valor; VidaMais cobre 30%; BemEstar cobre 50%.
- A idade do paciente deve estar entre 0 e 100 anos; valores fora desse intervalo são rejeitados pelo setter.
- Nome e CPF não podem ser nulos ou vazios — validado nos setters de `Pessoa` e `Paciente`.
- O percentual de cobertura de um convênio deve estar entre 0 e 1 (0% a 100%).
---

## Organização do Código

O projeto separa responsabilidades em três camadas distintas: `Main` cuida exclusivamente da interface com o usuário (menus e leitura de dados), `ClinicaServico` concentra toda a lógica de negócio e `Agenda` isola as operações sobre a lista de consultas.

As entidades do domínio encapsulam seus próprios dados e comportamentos — um `Paciente` sabe exibir seus dados, uma `Consulta` sabe se cancelar ou se remarcar, e cada forma de `Pagamento` sabe calcular seu próprio valor final.

A hierarquia de herança em três níveis (`Pessoa → Profissional → especializações`) evita duplicação de código ao centralizar atributos comuns na superclasse, enquanto cada nível acrescenta comportamentos e atributos específicos.
 
---

## Considerações Finais

O sistema VidaPlena implementa o ciclo completo de gestão de uma clínica multidisciplinar, desde o cadastro de pacientes e profissionais até o registro de atendimentos e processamento de pagamentos. O projeto aplica os principais pilares da Programação Orientada a Objetos em Java — encapsulamento, herança em múltiplos níveis, polimorfismo, ligação dinâmica, interfaces, tratamento de exceções e coleções — em um cenário de domínio realista, mantendo separação clara entre interface, lógica de negócio e modelo de dados.
 
