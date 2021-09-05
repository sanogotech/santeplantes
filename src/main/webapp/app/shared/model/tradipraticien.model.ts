import { IMaladie } from '@/shared/model/maladie.model';

export const enum Fiabilite {
  HAUT = 'HAUT',
  MOYEN = 'MOYEN',
  BAS = 'BAS',
}

export interface ITradipraticien {
  id?: number;
  nom?: string;
  prenom?: string;
  entreprise?: string;
  phone?: string;
  ville?: string;
  adresse?: string;
  fiabilite?: Fiabilite;
  ras?: boolean;
  maladies?: IMaladie[];
  maladie?: IMaladie;
}

export class Tradipraticien implements ITradipraticien {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public entreprise?: string,
    public phone?: string,
    public ville?: string,
    public adresse?: string,
    public fiabilite?: Fiabilite,
    public ras?: boolean,
    public maladies?: IMaladie[],
    public maladie?: IMaladie
  ) {
    this.ras = this.ras || false;
  }
}
