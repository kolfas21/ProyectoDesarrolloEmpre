<<<<<<< HEAD
package com.diagnosticos.Vitalia.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnostico {
    private String fecha;
    private String condicion;
    private List<String> sintomas;
    private Recomendacion recomendacion;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Recomendacion {
        private List<Medicamento> medicamentos;
        private String incapacidad;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Medicamento {
        private String nombre;
        private String dosis;
        private String frecuencia;
    }
}
=======
package com.diagnosticos.Vitalia.domain.model;

import java.util.List;

public class Diagnostico {
    private String fecha;
    private String condicion;
    private List<String> sintomas;
    private Recomendacion recomendacion;

    // getters/setters/constructores internos

    public static class Recomendacion {
        private List<Medicamento> medicamentos;
        private String incapacidad;
        // getters/setters/constructores
    }

    public static class Medicamento {
        private String nombre;
        private String dosis;
        private String frecuencia;
        // getters/setters/constructores
    }
}
>>>>>>> 39cab2f1dfea6e39219611a2c640b3b247bcb829
