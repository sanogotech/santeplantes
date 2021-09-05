import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import MaladieService from '../maladie/maladie.service';
import { IMaladie } from '@/shared/model/maladie.model';

import AlertService from '@/shared/alert/alert.service';
import { ITradipraticien, Tradipraticien } from '@/shared/model/tradipraticien.model';
import TradipraticienService from './tradipraticien.service';

const validations: any = {
  tradipraticien: {
    nom: {
      required,
    },
    prenom: {
      required,
    },
    entreprise: {
      required,
    },
    phone: {
      required,
    },
    ville: {
      required,
    },
    adresse: {
      required,
    },
    fiabilite: {
      required,
    },
    ras: {},
  },
};

@Component({
  validations,
})
export default class TradipraticienUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('tradipraticienService') private tradipraticienService: () => TradipraticienService;
  public tradipraticien: ITradipraticien = new Tradipraticien();

  @Inject('maladieService') private maladieService: () => MaladieService;

  public maladies: IMaladie[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tradipraticienId) {
        vm.retrieveTradipraticien(to.params.tradipraticienId);
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
    if (this.tradipraticien.id) {
      this.tradipraticienService()
        .update(this.tradipraticien)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('santeplantesApp.tradipraticien.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.tradipraticienService()
        .create(this.tradipraticien)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('santeplantesApp.tradipraticien.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveTradipraticien(tradipraticienId): void {
    this.tradipraticienService()
      .find(tradipraticienId)
      .then(res => {
        this.tradipraticien = res;
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
