package com.macrosoftas.santeplantes.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.macrosoftas.santeplantes.web.rest.TestUtil;

public class TraitementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Traitement.class);
        Traitement traitement1 = new Traitement();
        traitement1.setId(1L);
        Traitement traitement2 = new Traitement();
        traitement2.setId(traitement1.getId());
        assertThat(traitement1).isEqualTo(traitement2);
        traitement2.setId(2L);
        assertThat(traitement1).isNotEqualTo(traitement2);
        traitement1.setId(null);
        assertThat(traitement1).isNotEqualTo(traitement2);
    }
}
