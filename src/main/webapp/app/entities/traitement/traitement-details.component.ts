import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITraitement } from '@/shared/model/traitement.model';
import TraitementService from './traitement.service';

@Component
export default class TraitementDetails extends Vue {
  @Inject('traitementService') private traitementService: () => TraitementService;
  public traitement: ITraitement = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.traitementId) {
        vm.retrieveTraitement(to.params.traitementId);
      }
    });
  }

  public retrieveTraitement(traitementId) {
    this.traitementService()
      .find(traitementId)
      .then(res => {
        this.traitement = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
