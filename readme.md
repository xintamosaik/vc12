# Atomic CV

_“Craft smarter applications, faster.”_

## 🧠 Concept

**Atomic CV** is a modular system for crafting tailored CVs and cover letters by reusing well-tagged, context-rich content blocks. Instead of rewriting from scratch each time, users can build applications from small, reusable snippets matched to specific job requirements.

This project starts manual and low-tech to keep it flexible — just text snippets, tags, and matching logic — and can evolve to include LLMs and retrieval augmentation (RAG) later.

---

## 💡 Features (Phase 1 MVP)

- ✍️ **Snippet Library**
  - STAR stories, achievements, CV bullet points, and cover letter phrases
  - Stored as text + tag metadata
  - Editable and filterable in the UI

- 📋 **Job Ad Analyzer**
  - Paste in a job posting
  - Manually highlight or extract keywords
  - Assign tags for culture, skills, and values

- 🧷 **Keyword Mapper**
  - Match job keywords to tagged snippets
  - Visualise or list relevant connections

- 🧰 **Builder View**
  - Select snippets that align with job keywords
  - Compose a draft CV and cover letter
  - Manual editing encouraged

---

## 🗃 Data Model (MVP Format)

Use JSON or Markdown for now — later, MongoDB will store it.

### Example Snippet

```json
{
  "id": "snippet-001",
  "text": "Reduced monthly reconciliation errors by 30% through automating validation checks.",
  "tags": ["#job-1", "#system-thinking", "#initiative", "#results", "#cv"]
}
````

### Example Job Ad Keywords

```json
{
  "id": "job-002",
  "title": "Financial Analyst – X Corp",
  "keywords": ["problem solving", "automation", "team collaboration", "impact"]
}
```

---

## 🧱 Stack

* **Frontend:** Vue 3 + Vite
* **Backend (optional):** Spring Boot (Java)
* **Database (future):** MongoDB
* **Dev Tools:** VS Code, Docker (optional)

---

## 🔧 Suggested Folder Structure

```
atomic-cv/
├── frontend/             # Vue app
│   └── components/
├── backend/              # Java (Spring Boot) server
├── data/                 # JSON/Markdown for snippets & jobs (MVP)
├── public/               # Static assets
├── README.md
```

---

## 🔜 Future Plans

* 🔍 AI-assisted keyword extraction and snippet suggestion (LLM + RAG)
* 🔄 LinkedIn or PDF parsing for job ads
* 🧠 Semantic similarity matching
* 🪄 Autocomplete builder drafts with tone/style options

---

## 📣 Why?

Tailoring your application for every job is hard. Atomic CV makes it easier to **reuse your best work**, **speak their language**, and **build confidence** — fast.

