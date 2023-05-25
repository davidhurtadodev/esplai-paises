// export { default } from 'next-auth/middleware';
import { withAuth } from 'next-auth/middleware';

// // This function can be marked `async` if using `await` inside
// export function middleware(request) {
//   return NextResponse.redirect(new URL('/home', request.url));
// }

export default withAuth(
  // `withAuth` augments your `Request` with the user's token.
  function middleware(req) {
    console.log('token: ', req.nextauth.token);

    if (
      req.nextUrl.pathname.startsWith('/admin') &&
      req.nextauth.token?.role !== 'admin'
    )
      return NextResponse.rewrite(
        new URL('/auth/login?message=You Are Not Authorized!', req.url)
      );
    if (
      req.nextUrl.pathname.startsWith('/user') &&
      req.nextauth.token?.role !== 'user'
    )
      return NextResponse.rewrite(
        new URL('/auth/login?message=You Are Not Authorized!', req.url)
      );
  },
  {
    callbacks: {
      authorized: ({ token }) => !!token,
    },
  }
);
// See "Matching Paths" below to learn more
export const config = {
  matcher: '/infoss/:path',
};
