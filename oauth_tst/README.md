# OAuth2.0
[Baeldung](https://www.baeldung.com/java-ee-oauth2-implementation)
## Getting Ready
Every OAuth 2.0 service will require that you first register a new application, which also typically requires that you
first sign up as a developer with the service. After registering the application, you'll be given a client_id and a
client_secret that you'll use when your app interacts with the service.

One of the most important things when creating the application is to register one or more redirect URLs the application
will use. The redirect URLs are where the OAuth 2.0 service will return the user to after they have authorized the
application.

In order to be secure, the redirect URL must be an https endpoint to prevent the authorization code from being intercepted
during the authorization process.

It's important to generate a "state" parameter to use to protect the client from CSRF attacks. This is a random string
that the client generates and stores in the session.

A typical use of many common OAuth APIs is just to identify the user at the computer when logging in to a third-party app.

## OAuth2.0 vs OpenID Connect
OpenID Connect is an OAuth 2.0 extension. In summary, OAuth 2.0 is primarily focused on authorization and access control,
while OpenID Connect extends OAuth 2.0 to provide authentication services, including user identity verification and
profile information. OpenID Connect adds standardized ID tokens, scopes, and claims to simplify the authentication
process and enable SSO scenarios.

## OAuth2.0 vs Apache Syncope
* OAuth 2.0: OAuth 2.0 is an open standard protocol for authorization. It provides a framework for granting third-party
  applications limited access to protected resources on behalf of a resource owner (user).
  OAuth 2.0 enables secure API authorization, allowing users to grant specific permissions to applications without
  sharing their credentials.
* Apache Syncope: Apache Syncope is an open-source identity management and access governance system. It provides
  comprehensive features for managing identities, roles, and access control policies in an organization.
  It focuses on user provisioning, synchronization, and management of user accounts across various systems and applications.
* OAuth 2.0: OAuth 2.0 is primarily concerned with authorization, specifically providing a framework for delegated
  access to resources. It does not handle identity management or user account provisioning.
* Apache Syncope: Apache Syncope, on the other hand, is primarily focused on identity management. It offers features
  for managing user accounts, roles, entitlements, and access policies. It helps organizations centralize and
  streamline the management of user identities and their access privileges.
* OAuth 2.0: OAuth 2.0 is a protocol that can be implemented by various service providers, identity providers, and
  application developers. It is widely adopted and supported by many platforms and frameworks, making it easier to
  integrate into applications.
* Apache Syncope: Apache Syncope is a dedicated identity management system with its own set of features and APIs.
  It provides a comprehensive solution for managing user identities and access controls. While it offers integration
  capabilities through connectors and APIs, its primary focus is on identity management rather than being a generic
  authorization framework.

## OAuth 2.0 Overview
Roles
* Resource Owner
* Resource Server
* Client
* Authorization Server

Authorization Grant Types: how a client gets permission to use the resource owner's data
* Authorization Code: a web application, a native application, or a single-page application --> PKCE
* Refresh Token: suitable for web applications to renew their existing token
* Client Credentials: service-to-service communication --> when the resource owner isn't an end-user
* Resource Owner Password: first-party authentication of native applications, say when the mobile app needs
  its own login page

Authorization Code Grant FLow
* a client requests permission by redirecting to the authorization server's /authorize endpoint. To this endpoint,
  the application gives a callback endpoint
* an authorization server will usually ask the end-user - the resource owner - for permission. If the end-user
  grants permission, then the authorization server redirects back to the callback with a code.
* The application receives this code and then makes an authenticated call to the authorization server's /token endpoint.
* With the token in hand, the application makes its request to the API. It can ask the authorization server to verify
  the token using its /introspect endpoint. Or, if the token is self-contained, the resource server can optimize by locally
  verifying the token's signature, as is the case with JWT.