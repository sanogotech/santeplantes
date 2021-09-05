import { Component, Vue, Inject } from 'vue-property-decorator';

import { IMaladie } from '@/shared/model/maladie.model';
import MaladieService from './maladie.service';

@Component
export default class MaladieDetails extends Vue {
  @Inject('maladieService') private maladieService: () => MaladieService;
  public maladie: IMaladie = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.maladieId) {
        vm.retrieveMaladie(to.params.maladieId);
      }
    });
  }

  public retrieveMaladie(maladieId) {
    this.maladieService()
      .find(maladieId)
      .then(res => {
        this.maladie = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
