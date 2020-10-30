/*
 * Copyright (c) 2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ai.expert.nlapi.security;


public class DefaultCredentialsProvider extends CredentialsProvider {

    private CredentialsProvider providerChain;

    public DefaultCredentialsProvider() {
        providerChain = null;
        init();
    }

    private void init() {
        // set the chain of CredentialsProvider
        providerChain = new EnvironmentVariablesCredentialsProvider();
        providerChain.setNextCredentialsProvider(new SystemPropertyCredentialsProvider());
    }

    public Credential getCredentials() {
        // get credentials using the chain of CredentialsProvider
        return providerChain.solveCredentials();
    }
}