import { Component, Vue, Inject } from 'vue-property-decorator';

import { IPlante } from '@/shared/model/plante.model';
import PlanteService from './plante.service';

@Component
export default class PlanteDetails extends Vue {
  @Inject('planteService') private planteService: () => PlanteService;
  public plante: IPlante = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.planteId) {
        vm.retrievePlante(to.params.planteId);
      }
    });
  }

  public retrievePlante(planteId) {
    this.planteService()
      .find(planteId)
      .then(res => {
        this.plante = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
