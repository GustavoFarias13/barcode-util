
# 📦 Barcode Util

Projeto Spring Boot para geração e decodificação de códigos de barras, incluindo suporte a padrões populares como EAN-13, CODE 128 e GS1-128.

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Gradle
- SQLite (via Hibernate)
- ZXing (geração de imagem)
- Barcode4J (geração de GS1-128)
- JUnit (testes)

## 🚀 Como Executar

1. **Clone o repositório**
   ```bash
   git clone https://github.com/seuusuario/barcode-util.git
   cd barcode-util
   ```

2. **Execute o projeto com Gradle**
   ```bash
   ./gradlew bootRun
   ```

> A aplicação estará disponível em `http://localhost:8080`

## 🔄 Endpoints Disponíveis

### ▶️ Gerar código de barras

- **GET** `/api/barcode/generate?barcode=1234567890128`

  Gera a imagem (base64) de um código de barras válido.

  **Exemplo de resposta:**
  ```json
  {
    "type": "EAN-13",
    "base64": "iVBORw0KGgoAAAANSUhEUgAA..."
  }
  ```

### 📥 Decodificar código de barras

- **GET** `/api/barcode/decode?barcode=1234567890128`

  Retorna a descrição do código de barras decodificado.

  **Exemplo de resposta:**
  ```json
  {
    "type": "EAN-13",
    "data": {
      "country": "Brasil",
      "manufacturer": "7890123",
      "product": "456789"
    }
  }
  ```

## 📘 Tipos de Código de Barras Suportados

| Tipo       | Descrição                    | Suporta Geração | Suporta Decodificação |
|------------|------------------------------|------------------|------------------------|
| EAN-13     | Código europeu de 13 dígitos | ✅               | ✅                     |
| EAN-8      | Código europeu reduzido      | ✅               | ✅                     |
| UPC-A      | Código padrão americano      | ✅               | ✅                     |
| UPC-E      | Versão compacta do UPC-A     | ✅               | ✅                     |
| DUN-14     | Código logístico de unidades | ✅               | ✅                     |
| CODE 128   | Código alfanumérico flexível | ✅               | ✅                     |
| GS1-128    | Códigos com múltiplos AIs    | ✅               | ✅                     |

> A detecção do tipo é automática com base no valor informado.

## 🧪 Rodando Testes

```bash
./gradlew test
```

---

## 🧠 Sobre

Este projeto foi criado com o objetivo de estudar e oferecer uma ferramenta simples e extensível para análise de códigos de barras. Ideal para uso educacional, integração com sistemas logísticos ou aplicações comerciais.

---
