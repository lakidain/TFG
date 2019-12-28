export class Usuario {
  id: number;
  username: string;
  name_user: string;
  email_user: string;
  phone_user: string;
  password: string;
  name_company: string;
  roles:string[]=[];  //Por defecto no tiene roles
  enabled:boolean;
}
