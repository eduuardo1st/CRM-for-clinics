# Contrato da API - DECAD CRM (MVP)

## Autenticação

---

### 1. Fazer Login

-   **Endpoint:** `POST /api/auth/login`
-   **Descrição:** Autentica um usuário e retorna um token de acesso (JWT).
-   **Corpo da Requisição (Request Body):**
    ```json
    {
        "login": "string",
        "senha": "string"
    }
    ```
-   **Resposta de Sucesso (200 OK):**
    ```json
    {
        "token": "string (o token JWT gerado)"
    }
    ```

## Pacientes

---

### 1. Listar Todos os Pacientes

-   **Endpoint:** `GET /api/pacientes`
-   **Descrição:** Retorna uma lista com todos os pacientes.
-   **Resposta de Sucesso (200 OK):** `Array<Paciente>`
    ```json
    [
        {
            "idPaciente": 1,
            "nomeCompleto": "João da Silva",
            "email": "joao.silva@email.com",
            "cpf": "11122233344",
            "telefone": "61999998888"
        }
    ]
    ```

### 2. Criar Novo Paciente

-   **Endpoint:** `POST /api/pacientes`
-   **Descrição:** Adiciona um novo paciente.
-   **Corpo da Requisição (Request Body):**
    ```json
    {
        "nomeCompleto": "string",
        "email": "string",
        "cpf": "string",
        "telefone": "string"
    }
    ```
-   **Resposta de Sucesso (201 Created):** Retorna o paciente criado.

## Profissionais

---

### 1. Listar Todos os Profissionais

-   **Endpoint:** `GET /api/profissionais`
-   **Descrição:** Retorna uma lista com todos os profissionais.
-   **Resposta de Sucesso (200 OK):** `Array<Profissional>`
    ```json
    [
        {
            "idProfissional": 1,
            "nomeCompleto": "Dr. Carlos Andrade",
            "email": "carlos.a@clinica.com",
            "cpf": "33344455566",
            "cro_crm": "CRM-DF 12345",
            "especialidade": "Cardiologia",
            "telefone": "61988887777"
        }
    ]
    ```
-   _(As operações de Criar, Atualizar e Deletar Profissionais seguirão o mesmo padrão dos Pacientes)_

## Agenda e Agendamentos

---

### 1. Obter Agenda do Dia para um Profissional

-   **Endpoint:** `GET /api/agenda?profissionalId={id}&data={YYYY-MM-DD}`
-   **Descrição:** Retorna os horários de um dia para um profissional, indicando quais estão disponíveis ou ocupados.
-   **Parâmetros da URL:**
    -   `profissionalId`: O ID do profissional a ser consultado.
    -   `data`: A data no formato AAAA-MM-DD.
-   **Resposta de Sucesso (200 OK):**
    ```json
    [
        {
            "horario": "08:00",
            "status": "ocupado",
            "paciente": { "idPaciente": 5, "nomeCompleto": "Ana Paula" }
        },
        {
            "horario": "09:00",
            "status": "disponivel",
            "paciente": null
        },
        {
            "horario": "10:00",
            "status": "disponivel",
            "paciente": null
        }
    ]
    ```

### 2. Criar um Novo Agendamento

-   **Endpoint:** `POST /api/agendamentos`
-   **Descrição:** Cria um novo agendamento.
-   **Corpo da Requisição (Request Body):**
    ```json
    {
        "idPaciente": "number",
        "idProfissional": "number",
        "dataAgendamento": "string (YYYY-MM-DD)",
        "horaAgendamento": "string (HH:MM)"
    }
    ```
-   **Resposta de Sucesso (201 Created):** Retorna o agendamento criado com seu ID.

### 3. Cancelar um Agendamento

-   **Endpoint:** `DELETE /api/agendamentos/{id}`
-   **Descrição:** Remove um agendamento existente.
-   **Resposta de Sucesso (204 No Content):** Retorna uma resposta vazia.