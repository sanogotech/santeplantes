import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import MaladieService from '../maladie/maladie.service';
import { IMaladie } from '@/shared/model/maladie.model';

import AlertService from '@/shared/alert/alert.service';
import { ITraitement, Traitement } from '@/shared/model/traitement.model';
import TraitementService from './traitement.service';

const validations: any = {
  traitement: {
    nom: {
      required,
    },
    fiabilite: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class TraitementUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('traitementService') private traitementService: () => TraitementService;
  public traitement: ITraitement = new Traitement();

  @Inject('maladieService') private maladieService: () => MaladieService;

  public maladies: IMaladie[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.traitementId) {
        vm.retrieveTraitement(to.params.traitementId);
      }
      vm.initRelationships();
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
    if (this.traitement.id) {
      this.traitementService()
        .update(this.traitement)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('santeplantesApp.traitement.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.traitementService()
        .create(this.traitement)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('santeplantesApp.traitement.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveTraitement(traitementId): void {
    this.traitementService()
      .find(traitementId)
      .then(res => {
        this.traitement = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.maladieService()
      .retrieve()
      .then(res => {
        this.maladies = res.data;
      });
    this.maladieService()
      .retrieve()
      .then(res => {
        this.maladies = res.data;
      });
  }
}
