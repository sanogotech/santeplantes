import { IMaladie } from '@/shared/model/maladie.model';

export const enum Fiabilite {
  HAUT = 'HAUT',
  MOYEN = 'MOYEN',
  BAS = 'BAS',
}

export interface ITraitement {
  id?: number;
  nom?: string;
  fiabilite?: Fiabilite;
  maladies?: IMaladie[];
  maladie?: IMaladie;
}

export class Traitement implements ITraitement {
  constructor(
    public id?: number,
    public nom?: string,
    public fiabilite?: Fiabilite,
    public maladies?: IMaladie[],
    public maladie?: IMaladie
  ) {}
}
