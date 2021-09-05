import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITradipraticien } from '@/shared/model/tradipraticien.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import TradipraticienService from './tradipraticien.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Tradipraticien extends mixins(AlertMixin) {
  @Inject('tradipraticienService') private tradipraticienService: () => TradipraticienService;
  private removeId: number = null;

  public tradipraticiens: ITradipraticien[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTradipraticiens();
  }

  public clear(): void {
    this.retrieveAllTradipraticiens();
  }

  public retrieveAllTradipraticiens(): void {
    this.isFetching = true;

    this.tradipraticienService()
      .retrieve()
      .then(
        res => {
          this.tradipraticiens = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ITradipraticien): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTradipraticien(): void {
    this.tradipraticienService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('santeplantesApp.tradipraticien.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllTradipraticiens();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
