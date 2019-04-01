/*
MIT License

Copyright (c) 2019 Zachary Sherwin

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package org.jenkinsci.plugins.credentials;

import com.cloudbees.plugins.credentials.Credentials;
import com.cloudbees.plugins.credentials.CredentialsMatchers;
import hudson.model.Item;
import hudson.security.ACL;

import javax.annotation.Nonnull;
import java.util.Collections;

import static com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials;

public class CredentialsHelper {

  public static <T extends Credentials> T getCredentials(@Nonnull Class<T> type,
      @Nonnull String credentialsId, Item context) {
    return CredentialsMatchers.firstOrNull(lookupCredentials(
        type, context, ACL.SYSTEM,
        Collections.emptyList()), CredentialsMatchers.allOf(
        CredentialsMatchers.withId(credentialsId),
        CredentialsMatchers.instanceOf(type)));
  }
}