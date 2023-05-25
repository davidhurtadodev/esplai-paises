'use client';
import { useState } from 'react';
import CreateUserForm from '@/components/CreateUserForm';
import LoginForm from '@/components/LoginForm';
import styles from './page.module.css';
import Input from '@/components/Input';
import { signIn, signOut, useSession } from 'next-auth/react';

export default function Home() {
  // const [signInTest, setSignInTest] = useState(false);

  // return signInTest ? (
  return (
    <main className={styles.main}>
      <CreateUserForm />
      <button
        // onClick={(e) => setSignInTest(false)}
        onClick={(e) => signIn()}
        type="button"
        class="btn btn-primary"
      >
        Change to login
      </button>
    </main>
  );
  // ) : (
  // <main className={styles.main}>
  //   <LoginForm />
  //   <button onClick={(e) => signIn()} type="button" class="btn btn-primary">
  //     Change to SignIn
  //   </button>
  // </main>
}
