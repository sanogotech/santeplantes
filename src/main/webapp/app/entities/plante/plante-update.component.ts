import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import MaladieService from '../maladie/maladie.service';
import { IMaladie } from '@/shared/model/maladie.model';

import BienfaitService from '../bienfait/bienfait.service';
import { IBienfait } from '@/shared/model/bienfait.model';

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
  },
};

@Component({
  validations,
})
export default class PlanteUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('planteService') private planteService: () => PlanteService;
  public plante: IPlante = new Plante();

  @Inject('maladieService') private maladieService: () => MaladieService;

  public maladies: IMaladie[] = [];

  @Inject('bienfaitService') private bienfaitService: () => BienfaitService;

  public bienfaits: IBienfait[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.planteId) {
        vm.retrievePlante(to.params.planteId);
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

  public initRelationships(): void {
    this.maladieService()
      .retrieve()
      .then(res => {
        this.maladies = res.data;
      });
    this.bienfaitService()
      .retrieve()
      .then(res => {
        this.bienfaits = res.data;
      });
    this.maladieService()
      .retrieve()
      .then(res => {
        this.maladies = res.data;
      });
    this.bienfaitService()
      .retrieve()
      .then(res => {
        this.bienfaits = res.data;
      });
  }
}
