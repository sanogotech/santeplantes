import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IPlante } from '@/shared/model/plante.model';
import PlanteService from './plante.service';

@Component
export default class PlanteDetails extends mixins(JhiDataUtils) {
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
