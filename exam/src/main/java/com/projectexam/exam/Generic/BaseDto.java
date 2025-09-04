package com.projectexam.exam.Generic;

/**
 * Base pour les DTOs porteurs d'un identifiant technique commun.
 */

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public abstract class BaseDto {
    private Long id;
}
