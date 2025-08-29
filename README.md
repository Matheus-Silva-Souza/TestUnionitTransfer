Aplicação de Agendamento de Transferência Bancária
Descrição

Este projeto consiste em um formulário frontend em Vue.js para agendamento de transferências bancárias, integrado a uma API backend (Spring Boot) para persistência e processamento das transferências. A interface possui validações para garantir a integridade dos dados, formatação amigável e estilo visual profissional inspirado em sistemas bancários.

Decisões de Arquitetura
Frontend

Vue.js: Escolhido pela simplicidade, reatividade e capacidade de construção rápida de interfaces modernas.

Componentização: O formulário está encapsulado em um único componente (ScheduleForm.vue), facilitando manutenção e reutilização.

Validação no cliente: As validações de campos (contas com 10 dígitos, valor monetário formatado, data mínima) são feitas no frontend para melhorar a experiência do usuário, evitando chamadas desnecessárias ao backend.

Estilização scoped: CSS local ao componente para evitar conflitos globais e facilitar customização.

Backend

Spring Boot: Utilizado para criar uma API REST simples para agendamento e listagem das transferências.

Controle centralizado: O TransferController gerencia os endpoints REST.

Tratamento básico de erros: Captura exceções simples para retornar respostas apropriadas ao frontend (ex: 400 Bad Request).

O que o código faz?
ScheduleForm.vue

Exibe um formulário para agendar transferências, com os seguintes campos:

Conta de Origem (10 dígitos)

Conta de Destino (10 dígitos)

Valor da transferência (formato moeda BRL)

Data da transferência (não permite datas passadas)

Valida os campos em tempo real e exibe mensagens de erro específicas.

Formata o valor digitado automaticamente para o padrão monetário brasileiro.

Ao enviar o formulário, faz uma requisição POST para o backend enviando os dados da transferência.

Exibe mensagem de sucesso ou erro dependendo da resposta da API.

Tratamento de Erros

Frontend:

Validação do tamanho dos campos de conta, exibindo mensagem de erro ao usuário.

Impede seleção de data anterior ao dia atual.

Formatação de valor previne entradas inválidas.

Desabilita o botão "Agendar" enquanto o formulário estiver inválido.

Captura erros de requisição e exibe mensagem amigável.

Backend:

Valida dados recebidos (exemplo: verifica validade da transferência).

Retorna HTTP 400 para requisições mal formatadas.

Retorna HTTP 200 em caso de sucesso.

Testes
Manual

Preencha o formulário com valores válidos.

Tente inserir contas com menos ou mais que 10 dígitos e verifique as mensagens de erro.

Insira valores monetários e veja a formatação automática.

Tente selecionar datas anteriores ao dia atual (não deve ser possível).

Submeta o formulário e verifique mensagens de sucesso ou erro.

Teste respostas do backend simulando erros (ex: parando o servidor).

Automatizado

Frontend: Pode-se adicionar testes unitários com Jest + Vue Test Utils para validar a lógica de formatação e validação.

Backend: Testes unitários e de integração com JUnit para garantir a consistência do serviço TransferService e o comportamento do controller.

Rodando o projeto
Backend

Configure e execute o Spring Boot backend na porta 8080.

Certifique-se de que a API está disponível para receber requisições.

Frontend

Execute o projeto Vue.js (npm run serve ou equivalente).

Acesse http://localhost:8080 (ou outra porta configurada).

Interaja com o formulário para agendar transferências.
