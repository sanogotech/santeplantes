import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IPlante } from '@/shared/model/plante.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import PlanteService from './plante.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Plante extends mixins(AlertMixin) {
  @Inject('planteService') private planteService: () => PlanteService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;

  public plantes: IPlante[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllPlantes();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllPlantes();
  }

  public retrieveAllPlantes(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.planteService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.plantes = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
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

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllPlantes();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
