export class Usuario {
  id: number;
  username: string;
  name: string;
  email: string;
  phone: string;
  password: string;
  companyName: string;
  roles:string[]=[];  //Por defecto no tiene roles
}
