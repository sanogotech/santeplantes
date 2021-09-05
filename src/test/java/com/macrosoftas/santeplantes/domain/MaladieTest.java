package com.macrosoftas.santeplantes.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.macrosoftas.santeplantes.web.rest.TestUtil;

public class MaladieTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Maladie.class);
        Maladie maladie1 = new Maladie();
        maladie1.setId(1L);
        Maladie maladie2 = new Maladie();
        maladie2.setId(maladie1.getId());
        assertThat(maladie1).isEqualTo(maladie2);
        maladie2.setId(2L);
        assertThat(maladie1).isNotEqualTo(maladie2);
        maladie1.setId(null);
        assertThat(maladie1).isNotEqualTo(maladie2);
    }
}
