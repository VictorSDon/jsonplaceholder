package com.donato.jsonplaceholder.model.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoPlaceholder {
    private Long id;
    private String title;
    private Boolean completed;
}
