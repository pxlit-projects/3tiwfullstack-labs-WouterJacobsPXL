export class Article{
  artikelNummer:number;
  artikelNaam:string;
  aankoopPrijs:number;
  verkoopPrijs:number;
  image:string;


  constructor(artikelNummer: number, artikelNaam: string, aankoopPrijs: number, verkoopPrijs: number) {
    this.artikelNummer = artikelNummer;
    this.artikelNaam = artikelNaam;
    this.aankoopPrijs = aankoopPrijs;
    this.verkoopPrijs = verkoopPrijs;
    this.image = `assets/${artikelNaam}.jpg`
  }
}
