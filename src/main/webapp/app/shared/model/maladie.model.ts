import { IPlante } from '@/shared/model/plante.model';
import { ITradipraticien } from '@/shared/model/tradipraticien.model';
import { ITraitement } from '@/shared/model/traitement.model';

export const enum TypeMaladie {
  Cardiovasculaire = 'Cardiovasculaire',
  Cancers = 'Cancers',
  Systemenerveux = 'Systemenerveux',
  Cerveau = 'Cerveau',
  Respiratoires = 'Respiratoires',
  IntimeSexe = 'IntimeSexe',
  Peau = 'Peau',
  Digestif = 'Digestif',
  OsArticulation = 'OsArticulation',
}

export interface IMaladie {
  id?: number;
  nom?: string;
  type?: TypeMaladie;
  plantes?: IPlante[];
  tradipraticiens?: ITradipraticien[];
  traitements?: ITraitement[];
  plante?: IPlante;
  tradipraticien?: ITradipraticien;
  traitement?: ITraitement;
}

export class Maladie implements IMaladie {
  constructor(
    public id?: number,
    public nom?: string,
    public type?: TypeMaladie,
    public plantes?: IPlante[],
    public tradipraticiens?: ITradipraticien[],
    public traitements?: ITraitement[],
    public plante?: IPlante,
    public tradipraticien?: ITradipraticien,
    public traitement?: ITraitement
  ) {}
}
