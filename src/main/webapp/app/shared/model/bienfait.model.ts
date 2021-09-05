import { IPlante } from '@/shared/model/plante.model';

export interface IBienfait {
  id?: number;
  nom?: string;
  description?: string;
  vitamine?: string;
  mineraux?: string;
  plantes?: IPlante[];
  plante?: IPlante;
}

export class Bienfait implements IBienfait {
  constructor(
    public id?: number,
    public nom?: string,
    public description?: string,
    public vitamine?: string,
    public mineraux?: string,
    public plantes?: IPlante[],
    public plante?: IPlante
  ) {}
}
