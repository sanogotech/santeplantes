import { IMaladie } from '@/shared/model/maladie.model';
import { IBienfait } from '@/shared/model/bienfait.model';

export interface IPlante {
  id?: number;
  nomScientifique?: string;
  nomCommun?: string;
  famille?: string;
  genre?: string;
  maladies?: IMaladie[];
  bienfaits?: IBienfait[];
  maladie?: IMaladie;
  bienfait?: IBienfait;
}

export class Plante implements IPlante {
  constructor(
    public id?: number,
    public nomScientifique?: string,
    public nomCommun?: string,
    public famille?: string,
    public genre?: string,
    public maladies?: IMaladie[],
    public bienfaits?: IBienfait[],
    public maladie?: IMaladie,
    public bienfait?: IBienfait
  ) {}
}
