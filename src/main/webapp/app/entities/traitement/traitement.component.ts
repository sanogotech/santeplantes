import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITraitement } from '@/shared/model/traitement.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import JhiDataUtils from '@/shared/data/data-utils.service';

import TraitementService from './traitement.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Traitement extends mixins(JhiDataUtils, AlertMixin) {
  @Inject('traitementService') private traitementService: () => TraitementService;
  private removeId: number = null;

  public traitements: ITraitement[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTraitements();
  }

  public clear(): void {
    this.retrieveAllTraitements();
  }

  public retrieveAllTraitements(): void {
    this.isFetching = true;

    this.traitementService()
      .retrieve()
      .then(
        res => {
          this.traitements = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ITraitement): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTraitement(): void {
    this.traitementService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('santeplantesApp.traitement.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllTraitements();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
