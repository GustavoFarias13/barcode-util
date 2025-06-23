
# 📦 Barcode Decoder API

**Barcode Decoder** é uma API Java Spring Boot que decodifica diversos formatos de códigos de barras, retornando informações estruturadas e legíveis. Ela suporta códigos como EAN-13, EAN-8, UPC-A, UPC-E, DUN-14, GS1-128 e Code 128, com parsing específico para cada padrão.

---

## 🚀 Funcionalidades

- 🎯 **Detecção automática do tipo de código de barras**
- 🧠 **Parsing específico por estratégia para cada tipo (Strategy Pattern)**
- 📋 **Decodificação de códigos GS1-128 com interpretação de Application Identifiers (AIs)**
- 🏷️ **Consulta a prefixos e fabricantes (GTIN)**
- 🧪 **Validação inteligente com normalização opcional**
- 📚 **Armazenamento de dados auxiliares em banco SQLite embarcado**
- ⚠️ **Tratamento robusto de exceções e erros de parsing**
- 🧬 Extensível com novas estratégias de codificação

---

## 🧱 Arquitetura

O projeto segue o padrão **MVC + Strategy**, com foco em extensibilidade e responsabilidade única:

- `controller/` – expõe os endpoints REST
- `service/` – orquestra a lógica de negócios
- `strategy/` – implementa estratégias para cada padrão de código de barras
- `dto/` – objetos de resposta específicos para cada tipo
- `repository/` – acesso ao banco SQLite via Spring Data JPA
- `exception/` – tratamento centralizado de erros
- `util/` – ferramentas auxiliares (ex: normalização e detecção)

---

## 🔍 Tipos de Códigos Suportados

| Tipo       | Suporte | Estratégia Implementada | Observações |
|------------|---------|--------------------------|-------------|
| `EAN-13`   | ✅      | `EAN13Strategy`          | Validação e extração de país/fabricante |
| `EAN-8`    | ✅      | `EAN8Strategy`           | Decodificação direta |
| `UPC-A`    | ✅      | `UPCAStrategy`           | Compatível com EAN-13 |
| `UPC-E`    | ✅      | `UPCEStrategy`           | Expansão para UPC-A |
| `DUN-14`   | ✅      | `DUN14Strategy`          | Baseado em GTIN-13 |
| `GS1-128`  | ✅      | `GS1128Strategy`         | Interpretação de múltiplos AIs |
| `CODE 128` | ✅      | `Code128Strategy`        | Aceita qualquer payload textual |

---

## 🧪 Exemplos de Resposta

```json
// Exemplo para GS1-128
{
  "type": "GS1-128",
  "fields": [
    {
      "applicationIdentifier": "01",
      "value": "12345678901231",
      "description": "GTIN"
    },
    {
      "applicationIdentifier": "10",
      "value": "ABC123",
      "description": "Lote"
    }
  ]
}
```

---

## 🧰 Tecnologias Utilizadas

- ☕ Java 17+
- ⚙️ Spring Boot 3
- 🛢 SQLite com Hibernate Dialect customizado
- 🧪 JUnit para testes
- 🧬 Strategy Pattern para modularização
- 📖 Banco populado com AIs GS1 e Prefixos GTIN

---

## ⚙️ Como Executar

1. Clone o projeto:

```bash
git clone https://github.com/seuusuario/barcode-decoder.git
cd barcode-decoder
```

2. Rode o projeto com Spring Boot (IntelliJ ou terminal):

```bash
./gradlew bootRun
```

3. Acesse a API:

```
GET http://localhost:8080/api/barcode/decode?barcode=1234567890123
```

---

## 📂 Banco de Dados

O projeto utiliza um SQLite embarcado com tabelas:

- `application_identifiers` – códigos GS1
- `prefix` – prefixos de país/fabricante (GTIN)
- `system_numbers` – informações adicionais para análise de GTINs

---

## 💡 Expansão

Para adicionar um novo tipo:

1. Crie uma nova `Strategy` implementando `BarcodeStrategy`
2. Crie um `Response DTO`
3. Registre na `BarcodeStrategyFactory`

---

## 🛑 Tratamento de Erros

Erros são tratados via `GlobalExceptionHandler`, com respostas estruturadas:

```json
{
  "error": "Invalid barcode for type: EAN-13"
}
```

---

## 🧑‍💻 Autor

**Gustavo Farias**  
[LinkedIn](https://www.linkedin.com/in/gustavo-farias-a7b795190/)  
Desenvolvedor Java | Backend | APIs REST
