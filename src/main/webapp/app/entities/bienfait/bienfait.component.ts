import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IBienfait } from '@/shared/model/bienfait.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import BienfaitService from './bienfait.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Bienfait extends mixins(AlertMixin) {
  @Inject('bienfaitService') private bienfaitService: () => BienfaitService;
  private removeId: number = null;

  public bienfaits: IBienfait[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllBienfaits();
  }

  public clear(): void {
    this.retrieveAllBienfaits();
  }

  public retrieveAllBienfaits(): void {
    this.isFetching = true;

    this.bienfaitService()
      .retrieve()
      .then(
        res => {
          this.bienfaits = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IBienfait): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeBienfait(): void {
    this.bienfaitService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('santeplantesApp.bienfait.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllBienfaits();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
