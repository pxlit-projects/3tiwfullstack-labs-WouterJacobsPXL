export class Pokemon {
  id:number;
  name: string;
  type: string;
  icon: string;

  constructor(id: number, name: string, type: string, icon: string) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.icon = icon;
  }
}
