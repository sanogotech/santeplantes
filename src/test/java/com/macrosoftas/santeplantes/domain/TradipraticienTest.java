package com.macrosoftas.santeplantes.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.macrosoftas.santeplantes.web.rest.TestUtil;

public class TradipraticienTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tradipraticien.class);
        Tradipraticien tradipraticien1 = new Tradipraticien();
        tradipraticien1.setId(1L);
        Tradipraticien tradipraticien2 = new Tradipraticien();
        tradipraticien2.setId(tradipraticien1.getId());
        assertThat(tradipraticien1).isEqualTo(tradipraticien2);
        tradipraticien2.setId(2L);
        assertThat(tradipraticien1).isNotEqualTo(tradipraticien2);
        tradipraticien1.setId(null);
        assertThat(tradipraticien1).isNotEqualTo(tradipraticien2);
    }
}
