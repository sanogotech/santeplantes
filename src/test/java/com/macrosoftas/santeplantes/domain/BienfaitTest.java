package com.macrosoftas.santeplantes.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.macrosoftas.santeplantes.web.rest.TestUtil;

public class BienfaitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bienfait.class);
        Bienfait bienfait1 = new Bienfait();
        bienfait1.setId(1L);
        Bienfait bienfait2 = new Bienfait();
        bienfait2.setId(bienfait1.getId());
        assertThat(bienfait1).isEqualTo(bienfait2);
        bienfait2.setId(2L);
        assertThat(bienfait1).isNotEqualTo(bienfait2);
        bienfait1.setId(null);
        assertThat(bienfait1).isNotEqualTo(bienfait2);
    }
}
