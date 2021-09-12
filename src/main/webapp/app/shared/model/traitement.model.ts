import { IPlante } from '@/shared/model/plante.model';

export const enum Fiabilite {
  HAUT = 'HAUT',
  MOYEN = 'MOYEN',
  BAS = 'BAS',
}

export const enum TypeExtraction {
  Infusion = 'Infusion',
  Decoction = 'Decoction',
  Masseration = 'Masseration',
  Autre = 'Autre',
}

export const enum TypeTraitement {
  Preventif = 'Preventif',
  Curatif = 'Curatif',
  PreventifCuratif = 'PreventifCuratif',
}

export interface ITraitement {
  id?: number;
  nom?: string;
  fiabilite?: Fiabilite;
  typeExtraction?: TypeExtraction;
  mixtureEtposologie?: any;
  sourceInfos?: any;
  typeTraitement?: TypeTraitement;
  plante?: IPlante;
}

export class Traitement implements ITraitement {
  constructor(
    public id?: number,
    public nom?: string,
    public fiabilite?: Fiabilite,
    public typeExtraction?: TypeExtraction,
    public mixtureEtposologie?: any,
    public sourceInfos?: any,
    public typeTraitement?: TypeTraitement,
    public plante?: IPlante
  ) {}
}
