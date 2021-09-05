import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import PlanteService from '../plante/plante.service';
import { IPlante } from '@/shared/model/plante.model';

import AlertService from '@/shared/alert/alert.service';
import { IBienfait, Bienfait } from '@/shared/model/bienfait.model';
import BienfaitService from './bienfait.service';

const validations: any = {
  bienfait: {
    nom: {
      required,
    },
    description: {
      required,
    },
    vitamine: {},
    mineraux: {},
  },
};

@Component({
  validations,
})
export default class BienfaitUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('bienfaitService') private bienfaitService: () => BienfaitService;
  public bienfait: IBienfait = new Bienfait();

  @Inject('planteService') private planteService: () => PlanteService;

  public plantes: IPlante[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.bienfaitId) {
        vm.retrieveBienfait(to.params.bienfaitId);
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
    if (this.bienfait.id) {
      this.bienfaitService()
        .update(this.bienfait)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('santeplantesApp.bienfait.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.bienfaitService()
        .create(this.bienfait)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('santeplantesApp.bienfait.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveBienfait(bienfaitId): void {
    this.bienfaitService()
      .find(bienfaitId)
      .then(res => {
        this.bienfait = res;
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
    this.planteService()
      .retrieve()
      .then(res => {
        this.plantes = res.data;
      });
  }
}
