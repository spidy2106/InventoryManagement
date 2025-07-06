export interface LoginResponse {
  token: string;
  role: 'USER' | 'ADMIN';
}
