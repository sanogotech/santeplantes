export interface IPlante {
  id?: number;
  nomScientifique?: string;
  nomCommun?: string;
  famille?: string;
  genre?: string;
  photoContentType?: string;
  photo?: any;
  bienfaits?: string;
  imageBienfaitsContentType?: string;
  imageBienfaits?: any;
  typeMaladies?: string;
  maladies?: string;
}

export class Plante implements IPlante {
  constructor(
    public id?: number,
    public nomScientifique?: string,
    public nomCommun?: string,
    public famille?: string,
    public genre?: string,
    public photoContentType?: string,
    public photo?: any,
    public bienfaits?: string,
    public imageBienfaitsContentType?: string,
    public imageBienfaits?: any,
    public typeMaladies?: string,
    public maladies?: string
  ) {}
}
