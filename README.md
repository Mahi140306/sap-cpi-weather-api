# Real-Time Weather API Integration | SAP BTP Cloud Integration (CPI)

An end-to-end integration project in **SAP Cloud Integration (CPI)** that receives a city name from a client, orchestrates external REST API calls to **OpenWeatherMap** (Geocoding & Current Weather), transforms the payload using **Groovy Scripting**, dispatches automated emails via the **Mail Adapter**, and implements robust error handling using an **Exception Subprocess**.

---

## 🏗️ Architecture & Flow Overview

```text
[Postman / Client] 
       │ (1. Inbound HTTPS Request with City name)
       ▼
[HTTPS Sender Adapter] 
       │
       ▼
[Content Modifier 1] ───► Stores City & API Key into Exchange Properties
       │
       ▼
[Request-Reply 1] ─────► Calls OpenWeather Geocoding API (Retrieves Lat & Lon)
       │
       ▼
[Content Modifier 2] ───► Extracts Lat & Lon from response into Properties
       │
       ▼
[Request-Reply 2] ─────► Calls OpenWeather Current Weather API (Gets Weather JSON)
       │
       ▼
[Groovy Script] ────────► Parses raw JSON and transforms it into formatted JSON
       │
       ├────────────────► [Mail Adapter] (Sends Automated Email Notification)
       ▼
[Response to Client] ───► Returns clean formatted JSON to caller (Postman)
