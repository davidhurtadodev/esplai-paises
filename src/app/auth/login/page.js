'use client';
import LoginForm from '@/components/LoginForm';
import styles from './page.module.css';
import Link from 'next/link';

const LoginPage = () => {
  return (
    <main className={styles.main}>
      <LoginForm />
      <Link href="/">
        <button type="button" class="btn btn-primary">
          Change to Sign In
        </button>
      </Link>
    </main>
  );
};

export default LoginPage;
