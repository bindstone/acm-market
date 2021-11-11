export class Product {
  id: number
  name: String
  currency: String
  price: number
  priceEur: number
  created: Date
  updated: Date

  constructor(id: number, name: String, currency: String, price: number, priceEur: number, created: Date, updated:Date) {
    this.id = id;
    this.name = name;
    this.currency = currency;
    this.price = price;
    this.priceEur = priceEur;
    this.created = created;
    this.updated = updated;
  }
}
