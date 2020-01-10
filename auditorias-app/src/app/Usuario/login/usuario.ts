export class Usuario {
  id: number;
  username: string;
  name_user: string;
  email_user: string;
  phone_user: string;
  password: string;
  id_company: number;
  roles: string[] = [];  //Por defecto no tiene roles
  enabled: boolean;
}
