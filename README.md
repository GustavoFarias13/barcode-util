# 📦 Barcode Decoder

**Barcode Decoder** é um projeto desenvolvido em **Java** com **Spring Boot** que permite a **análise de códigos de barras digitados pelo usuário**.

O sistema é capaz de:
- Identificar o tipo de codificação (ex: `EAN-13`, `UPC-A`, etc.)
- Interpretar os segmentos do código:
  - 🌍 País de origem  
  - 🏭 Fabricante  
  - 📦 Tipo de produto  
  - ✅ Dígito verificador

---

## 🎯 Objetivo

Este projeto foi criado com foco em:

- 🧠 **Educação**: auxiliar estudantes a entenderem como funcionam os códigos de barras.
- 🛠️ **Depuração e Testes**: útil para desenvolvedores que queiram validar ou inspecionar códigos.
- 👨‍💻 **Exploração**: uma ferramenta leve e funcional para entusiastas da área.

---

## 🚀 Funcionalidades

- Análise de códigos digitados diretamente na interface
- Reconhecimento automático do padrão de codificação
- Exibição segmentada e explicada de cada parte do código
- Interface Web simples usando Spring MVC (ou acesso via REST API)

---

## 🖥️ Como Rodar Localmente

```bash
git clone https://github.com/gustavofarias/barcode-decoder.git
cd barcode-decoder
./mvnw spring-boot:run
