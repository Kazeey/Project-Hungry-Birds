export class favori {
    public id_utilisateur : number
    public id_favori : number;

    constructor(id_utilisateur: number, id_favori: number)
    {
        this.id_utilisateur = id_utilisateur;
        this.id_favori = id_favori;
    }
}