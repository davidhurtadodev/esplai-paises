'use client';
import { useState } from 'react';
import { signIn } from 'next-auth/react';
import Input from './Input';
import loginService from '@/lib/login.service';

const LoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const onSubmit = async () => {
    const result = await signIn('credentials', {
      email: username,
      password: password,
      redirect: true,
      callbackUrl: '/',
    });
    console.log(result);
  };

  //   const handleLogin = async (e) => {
  //     e.preventDefault();

  //     const user = await loginService.login({ username, password });
  //     console.log(user);
  //     setNewUsername('');
  //     setNewPassword('');
  //   };

  return (
    <form>
      <div className="mb-3">
        <label for="exampleInputEmail1" className="form-label">
          Email
        </label>
        <Input
          value={username}
          type="email"
          onChange={(e) => setUsername(e.target.value)}
        />
      </div>

      <div className="mb-3">
        <label for="exampleInputPassword1" className="form-label">
          Password
        </label>
        <Input
          value={password}
          type="password"
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      <button
        type="submit"
        onClick={() => onSubmit()}
        className="btn btn-primary mb-5"
      >
        Login
      </button>
    </form>
  );
};

export default LoginForm;
