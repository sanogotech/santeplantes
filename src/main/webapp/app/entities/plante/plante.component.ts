import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IPlante } from '@/shared/model/plante.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import JhiDataUtils from '@/shared/data/data-utils.service';

import PlanteService from './plante.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Plante extends mixins(JhiDataUtils, AlertMixin) {
  @Inject('planteService') private planteService: () => PlanteService;
  private removeId: number = null;

  public plantes: IPlante[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllPlantes();
  }

  public clear(): void {
    this.retrieveAllPlantes();
  }

  public retrieveAllPlantes(): void {
    this.isFetching = true;

    this.planteService()
      .retrieve()
      .then(
        res => {
          this.plantes = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IPlante): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removePlante(): void {
    this.planteService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('santeplantesApp.plante.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllPlantes();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
