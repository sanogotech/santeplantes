import { Component, Vue, Inject } from 'vue-property-decorator';

import { IBienfait } from '@/shared/model/bienfait.model';
import BienfaitService from './bienfait.service';

@Component
export default class BienfaitDetails extends Vue {
  @Inject('bienfaitService') private bienfaitService: () => BienfaitService;
  public bienfait: IBienfait = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.bienfaitId) {
        vm.retrieveBienfait(to.params.bienfaitId);
      }
    });
  }

  public retrieveBienfait(bienfaitId) {
    this.bienfaitService()
      .find(bienfaitId)
      .then(res => {
        this.bienfait = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
