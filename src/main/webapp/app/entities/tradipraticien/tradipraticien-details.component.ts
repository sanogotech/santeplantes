import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITradipraticien } from '@/shared/model/tradipraticien.model';
import TradipraticienService from './tradipraticien.service';

@Component
export default class TradipraticienDetails extends Vue {
  @Inject('tradipraticienService') private tradipraticienService: () => TradipraticienService;
  public tradipraticien: ITradipraticien = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tradipraticienId) {
        vm.retrieveTradipraticien(to.params.tradipraticienId);
      }
    });
  }

  public retrieveTradipraticien(tradipraticienId) {
    this.tradipraticienService()
      .find(tradipraticienId)
      .then(res => {
        this.tradipraticien = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
