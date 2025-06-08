package grapefrui.xyz.backend;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "intel")
record Intel(String id, String title, String description, String content) {}