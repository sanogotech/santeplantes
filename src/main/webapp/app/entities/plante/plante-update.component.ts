import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IPlante, Plante } from '@/shared/model/plante.model';
import PlanteService from './plante.service';

const validations: any = {
  plante: {
    nomScientifique: {},
    nomCommun: {
      required,
    },
    famille: {},
    genre: {},
    photo: {},
    bienfaits: {
      required,
      maxLength: maxLength(350),
    },
    imageBienfaits: {},
    typeMaladies: {
      required,
      maxLength: maxLength(300),
    },
    maladies: {
      required,
      maxLength: maxLength(350),
    },
  },
};

@Component({
  validations,
})
export default class PlanteUpdate extends mixins(JhiDataUtils) {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('planteService') private planteService: () => PlanteService;
  public plante: IPlante = new Plante();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.planteId) {
        vm.retrievePlante(to.params.planteId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.plante.id) {
      this.planteService()
        .update(this.plante)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('santeplantesApp.plante.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.planteService()
        .create(this.plante)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('santeplantesApp.plante.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrievePlante(planteId): void {
    this.planteService()
      .find(planteId)
      .then(res => {
        this.plante = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public clearInputImage(field, fieldContentType, idInput): void {
    if (this.plante && field && fieldContentType) {
      if (this.plante.hasOwnProperty(field)) {
        this.plante[field] = null;
      }
      if (this.plante.hasOwnProperty(fieldContentType)) {
        this.plante[fieldContentType] = null;
      }
      if (idInput) {
        (<any>this).$refs[idInput] = null;
      }
    }
  }

  public initRelationships(): void {}
}
