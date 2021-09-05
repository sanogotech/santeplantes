import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import PlanteService from '../plante/plante.service';
import { IPlante } from '@/shared/model/plante.model';

import TradipraticienService from '../tradipraticien/tradipraticien.service';
import { ITradipraticien } from '@/shared/model/tradipraticien.model';

import TraitementService from '../traitement/traitement.service';
import { ITraitement } from '@/shared/model/traitement.model';

import AlertService from '@/shared/alert/alert.service';
import { IMaladie, Maladie } from '@/shared/model/maladie.model';
import MaladieService from './maladie.service';

const validations: any = {
  maladie: {
    nom: {
      required,
    },
    type: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class MaladieUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('maladieService') private maladieService: () => MaladieService;
  public maladie: IMaladie = new Maladie();

  @Inject('planteService') private planteService: () => PlanteService;

  public plantes: IPlante[] = [];

  @Inject('tradipraticienService') private tradipraticienService: () => TradipraticienService;

  public tradipraticiens: ITradipraticien[] = [];

  @Inject('traitementService') private traitementService: () => TraitementService;

  public traitements: ITraitement[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.maladieId) {
        vm.retrieveMaladie(to.params.maladieId);
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
    if (this.maladie.id) {
      this.maladieService()
        .update(this.maladie)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('santeplantesApp.maladie.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.maladieService()
        .create(this.maladie)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('santeplantesApp.maladie.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveMaladie(maladieId): void {
    this.maladieService()
      .find(maladieId)
      .then(res => {
        this.maladie = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.planteService()
      .retrieve()
      .then(res => {
        this.plantes = res.data;
      });
    this.tradipraticienService()
      .retrieve()
      .then(res => {
        this.tradipraticiens = res.data;
      });
    this.traitementService()
      .retrieve()
      .then(res => {
        this.traitements = res.data;
      });
    this.planteService()
      .retrieve()
      .then(res => {
        this.plantes = res.data;
      });
    this.tradipraticienService()
      .retrieve()
      .then(res => {
        this.tradipraticiens = res.data;
      });
    this.traitementService()
      .retrieve()
      .then(res => {
        this.traitements = res.data;
      });
  }
}
